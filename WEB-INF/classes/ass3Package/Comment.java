package ass3Package;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
public class Comment implements Serializable {

    private String comment;
    private User author;
    private LocalTime time;
    private LocalDate date;
    private boolean edited = false;

    private int commentNum;

    Comment(){


    }

    Comment(String comment, User author){

        //comment constructor w/ variables

        //set the time on creation
        time = LocalTime.now();
        date = LocalDate.now();

        //set values
        this.comment = comment;
        this.author = author;
    }

    public void setComment(String comment, User author) {
        //set the variables of the object
        //updates the time / date as what they were on creation
        time = LocalTime.now();
        date = LocalDate.now();
        this.author = author;
        this.comment = comment;
    }

    public String getComment(){

        //returns a comment
        return this.comment;
    }

    public Date getTime(){

        Date date = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        DateFormat d2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        DateFormat outputformat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        try{
            date = d2.parse(time.toString() + date.toString());
            return date;
        } catch (ParseException e){
            e.printStackTrace();
        }



        return null;
        //format the date + time to make one string
        //need this for sorting the linked list
    }

    public void editComment(String newComment){

        //edit the comment with new text


        this.edited = true;
        this.comment = newComment;
    }

    public void setAuthor(User author){

        //set the author of a comment

        this.author = author;
    }

    public void setCommentPos(int commentNumber){

        //basically a unique identifier
        this.commentNum = commentNumber;
    }

    public int getCommentPos(){

        //return the unique ID
        return commentNum;
    }

    public String getAuthor() {

        //return the user name as a string
        return author.getUid();
    }

    public User getAuthorObect(){

        //return the user as an object
        return author;
    }
}
