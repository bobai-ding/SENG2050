# SENG2050
assignment 3 stuff with the things and the stuff


google doc w/ site planning
https://docs.google.com/document/d/150ViThCGTeSyuNGluRY9Wxn13LGmbo5C23dWn6JjlFc/edit?usp=sharing

EXTRA REQUIREMENTS COMPLETED
“Sometimes the language that IT staff use is complicated, maybe users should be able to add comments to the Knowledge-Base articles to help other users.” (weight 10) 


CURRENT TODO by the 9th

General stuff (and in no specific order);

1. [x] Make it look nice (or not...) 

2. [x] User login w/ verification 
    - [x] Look into tomcat users

3. [ ] Need to have user feedback when interacting with stuff 
    
5. [x] Jsp still to make 
    - [x] Knowledge base
        - [x] Filter this by status in report ("Completed" or "Resolved")
    - [X] View open reports
        - [x] Filter this by status in report ("inprogress" or "new")
        - [x] User should only see report related to them (Probably name)
    - [x] View all reports 
        - [x] Should only be viewable by staff 
        - [x] Need to have a way to sort this list or grouped by status
    - [x] Mark as completed 
        - [x] Staff need a way to mark a report as completed 
    - [x] Add completed reports to knowledge base
        - [x] Staff only
    - [x] User accepts completed report 
    
7. [X] Need to add to report db 
    - [X] date time resolved
    
8. [x] Need to add to user db 
    - [x] Name
    - [x] surname
    - [x] email
    - [x] contact number

9. [x] Servlets!!! 
    - [x] CreateReport.java needs to take data from createReport.jsp and add to database
    - [x] editReport.java needs to take data from ViewReport.jsp and edit the status / knowledge bool of a specific report
    - [ ] ALL SERVLETS NEED TO HANDLE IMAGES
    
10. [ ]  FINAL DEBUG / ADDITIONS: 
    - [ ] User's need to be able to change state of report
    - [ ] No comments on resolved except if in knowledge base
    - [ ] Add resolution details to report
    - [ ] staff can add to knowledge base when resolved AND completed
    - [ ] multi user safety
    - [ ] HTML validity
    - [ ] request parameter security?
    
11. [x] Completed aditional requirements 
    - [x] 3.“Sometimes the language that IT staff use is complicated, maybe users should be able to add comments to the Knowledge-Base articles to help other users.” (weight 10)
    - [x] 14. “We should be able to view Knowledge-Base articles sorted by their categories” – User. (weight 5)
    - [x] 15. “We should be able to sort the issues by the date that they were reported” – IT staff. (weight 5)
    - [x] 16. “It would be nice if we could have key-word search capabilities” – User. (weight 20)
    - [x] 17. “Not all incidents will make it to the Knowledge-Base right away. We need a useful way to search over the old incidents that aren’t in the Knowledge-Base” – IT staff. (weight 20)
    - [x] Total: 60/30
    
12. [ ] Will;

    1. Database stuff _
    2. 
    3.

13. [ ] Lachlan;

    1. Objects _
    2. User feedback
    3.

14. [ ] Simi;

    1. jsp
    2. servlets
    3. sorting
