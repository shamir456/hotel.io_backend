package com.Hotel.io.Project.domain;




import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
public class Booking  {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String checkin, checkout;

    @Size(min = 1, max =10)
    private int bill;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;





//    @ManyToOne
//    private User user;

    //@JsonManagedReference


    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


}