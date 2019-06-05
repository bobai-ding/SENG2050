package ass3Package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;

public class Report implements Serializable{

    private User author;

    private java.time.LocalTime time;
    private java.time.LocalDate date;

    private String reportContent;

    private LinkedList<Comment> comments = new LinkedList<>();

    Report(){

    }

    public void newReport(String report, User author){

        //create a new report with a report string and user object

        time = java.time.LocalTime.now();
        date = java.time.LocalDate.now();
        this.reportContent = report;
        this.author = author;

        System.out.println(time);
        System.out.println(date);

        System.out.println(report);
        System.out.println(author.getUid());
    }

    public void editReport(String report){

        //need to format the time to remove the ms

        //probs need to format the date to aus standards

        time = java.time.LocalTime.now();
        date = java.time.LocalDate.now();
        this.reportContent = report;
    }

    public void newCommentObject(Comment comment){

        //create a new comment with a comment object

        //set the number of the comment to the total of the size of the array

        comment.setCommentPos(comments.size()+1);

        comments.add(comment);

    }

    public void newCommentString(String comment, User author){

        //create a new comment with a user object and a string

        Comment newComment = new Comment(comment, author);

        newComment.setCommentPos(comments.size()+1);

        comments.add(newComment);

    }

    public void editComment(int pos, String newComment) {
        //searches the comment linked list for a comment base on a position given

        for(Comment s : comments){
            if(s.getCommentPos() == pos){

                //when it finds the position, it changes the comment

                s.editComment(newComment);
            }
        }
    }

    public Comment[] getComments(){

        //returns all the current comments that a report has in an array

        Comment[] outputComments = new Comment[comments.size()];

        int i = 0;

        for(Comment s : comments){
            outputComments[i] = s;
            i++;
        }

        return outputComments;
    }

    public void setAuthor(User author){
        this.author = author;
    }

    public User getAuthor(){
        return author;
    }

    //have an array list of the comment shiz

}
