package onekr.cardservice.card.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.Comment;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CardCommentBiz {

	Comment saveCardComment(@NotNull @Min(1) Long cardId, Long commentId,
			@NotNull @Min(1) Long userId,
			@NotBlank String userName, @NotBlank String content, String json);

	List<Comment> listAll(@NotNull Long cardId);

	void deleteComment(@NotNull @Min(1) Long cardId, Long commentId);
}