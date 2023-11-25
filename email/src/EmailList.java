import java.util.TreeSet;

public class EmailList {
private String email;

    public EmailList(){
    }

    private TreeSet <String> emailList = new TreeSet<>();

    public void addEmail(String email) {
        emailList.add(email);
    }
    public TreeSet getEmailList(){
        return emailList;
    }
}


