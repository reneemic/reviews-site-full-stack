package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CommentControllerTest {

	@InjectMocks
	private CommentController underTest;

	@Mock
	private CommentRepository commentRepo;

	@Mock
	private Comment comment1;
	@Mock
	private Comment comment2;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddASingleCommentToModel() {
		Long commentId = 1L;
		when(commentRepo.findOne(commentId)).thenReturn(comment1);
		underTest.findOneComment(commentId, model);
		verify(model).addAttribute("comments", comment1);
	}

	@Test
	public void shouldReturnASingleComment() {
		String template = underTest.findOneComment(1L, model);
		assertThat(template, is("comment"));
	}

	@Test
	public void shouldReturnAllComments() {
		Collection<Comment> allComments = Arrays.asList(comment1, comment2);
		when(commentRepo.findAll()).thenReturn(allComments);
		underTest.findAllComments(model);
		verify(model).addAttribute("comments", allComments);
	}
}