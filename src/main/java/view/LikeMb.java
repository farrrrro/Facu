package view;

import javax.inject.Inject;
import javax.inject.Named;

import view.AuthMb;
import controller.LikeController;
import model.Post;;

@Named
public class LikeMb {

	@Inject
	private AuthMb authMb;

	@Inject
	private LikeController likeCtrl;


	public void like(Post p) {
		likeCtrl.like(authMb.getCurrentUser(), p);

	}

	public String amount(Post p) {
		return likeCtrl.amount(p) + "";
	}
}