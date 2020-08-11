package com.example.demo.web;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.MyUserDetailsService;
import com.example.demo.editor.Article;

import com.example.demo.models.User;
import com.example.demo.models.UserForm;

@Controller
public class HomeController {
	@Autowired
	private UserForm userForm;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private ArticleRepository articleRepo;

	@GetMapping("/")
	public String home(Model model) {

		// article links are displayed on the home page
		Page<Article> allArticles = articleRepo.findAll(PageRequest.of(0, 5));

		model.addAttribute("articles", allArticles);
		return "home";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/user")
	public String userPage() {

		return "<h1>Hello User</h1>";

	}

	@GetMapping("/admin")
	public String adminPage() {

		return "<h1>Hello Admin</h1>";

	}

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("userForm", userForm);
		return "register";
	}

	// if successful want user information to be persisted to database and returns
	// the user to home screen

	@PostMapping("/register")
	public String processRegistration(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "register";
		}
		User user = userForm.createUser();

		myUserDetailsService.saveUser(user);

		redirectAttributes.addFlashAttribute("user", user);

		return "redirect:/";

	}

	@GetMapping("/createArticle")
	public String createArticle(RedirectAttributes model, @ModelAttribute("article") Article article) {

		if (article == null) {
			article = new Article();
		}

		model.addFlashAttribute("article", article);
		return "article_creator";
	}

	@PostMapping("/publishArticle")
	public String publishArticle(@ModelAttribute("article") Article articleDraft, Principal principal, int id) {

		Optional<Article> articles = articleRepo.findById(id);

		Article article = articles.isEmpty() ? null : articles.get();

		if (article == null) {
			article = articleDraft;
			article.setAuthor(principal.getName());

		} else {
			article.setTitle(articleDraft.getTitle());
			article.setSummary(articleDraft.getSummary());
		}
		article.createTimeStamp();
		articleRepo.save(article);
		return "redirect:/";
	}

	// read article method
	@GetMapping("/viewArticle")
	public String readArticle(int articleId, Model model) {

		Optional<Article> articles = articleRepo.findById(articleId);
		Article article = articles.get();

		Parser parser = Parser.builder().build();
		Node document = parser.parse("# " + article.getTitle() + "\n" + article.getAuthor() + " "
				+ article.getTimeStamp() + " " + article.getUpdatedTimeStamp() + "\n" + article.getSummary());

		HtmlRenderer render = HtmlRenderer.builder().build();

		String articleSummary = render.render(document);
		model.addAttribute("article", article);
		model.addAttribute("articleSummary", articleSummary);
		return "article";

	}

	// edit article
	@GetMapping("/updateArticle")
	public String updateArticle(Integer id, RedirectAttributes model) {

		Optional<Article> articles = articleRepo.findById(id);

		Article article = articles.isEmpty() ? null : articles.get();

		model.addFlashAttribute("article", article);
		return "redirect:/createArticle";
	}

}
