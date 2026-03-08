package lab09_1;

public class Fiction implements Book {

	@Override
	public String getContent() {
		return "Easy Hacking is a Fiction's content from"+ PUBLISHER +".";
	}

}
