package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

	@Resource
	CommentRepository commentRepo;

	@Resource
	ReviewRepository reviewRepo;

	@RequestMapping("/show-comments")
	public String findAllComments(Model model) {
		model.addAttribute("comments", commentRepo.findAll());
		return "comments";
	}

	@RequestMapping("/comment")
	public String findOneComment(@RequestParam(value = "id") Long id, Model model) {
		model.addAttribute("comments", commentRepo.findOne(id));
		return "comment";
	}

//	@RequestMapping("/add-comment")
//	public String addComment(String comment, Long id) {
//		Optional<Review> review = reviewRepo.findById(id);
//		Comment newComment = new Comment(comment, review);
//		commentRepo.save(newComment);
//		return "redirect:/review?id=" + id;
//	}
}
