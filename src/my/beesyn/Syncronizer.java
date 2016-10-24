/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.beesyn;

import com.chargebee.*;
import com.chargebee.models.*;
import java.util.ArrayList;
import java.security.MessageDigest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author Christos Theodorakis
 */

public class Syncronizer {
    
    private ArrayList <Identifier> idf;
    private ListResult [] retr_list ;

    public Syncronizer(){
        Environment.configure("cartelera-test", "test_EvCXyW0ujslmxFj6ox9esPJcA1QBYGbe");
        retr_list = new ListResult[11];
        idf = new ArrayList();
    }
    
    public Syncronizer(String s_name, String k_name){
        
        Environment.configure(s_name, k_name);
        retr_list = new ListResult[11];
        idf = new ArrayList();
    }
    
    public ArrayList<Identifier> GetInfo(){
        return(idf);
    }
    
    public String retrieve(){
        ListResult ab_list, tmp;
        Result card;
        MessageDigest md;        
        try{
            Identifier t_idf = new Identifier();
            md = MessageDigest.getInstance("SHA-256");
            ab_list = Customer.list().request();
            
            for(ListResult.Entry entry:ab_list){
               t_idf.set_cat("Customer");              
               t_idf.set_id(entry.customer().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Subscription.list().request();
            t_idf.set_cat("Subscription");
            for(ListResult.Entry entry:ab_list){
                //System.out.println(entry.subscription().id());
                t_idf.set_id(entry.subscription().id());
                t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
                t_idf.add(entry.toString());
                idf.add(t_idf.cclone());
            }            
            
            ab_list = Invoice.list().request();
            t_idf.set_cat("Invoice");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.invoice().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = CreditNote.list().request();
            t_idf.set_cat("CreditNote");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.creditNote().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Order.list().request();
            t_idf.set_cat("Order");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.order().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Transaction.list().request();
            t_idf.set_cat("Transaction");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.transaction().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Plan.list().request();
            t_idf.set_cat("Plan");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.plan().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Addon.list().request();
            t_idf.set_cat("Addon");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.addon().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Coupon.list().request();
            t_idf.set_cat("Coupon");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.coupon().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Event.list().request();
            t_idf.set_cat("Event");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.event().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            ab_list = Comment.list().request();
            t_idf.set_cat("Comment");
            for(ListResult.Entry entry:ab_list){
               t_idf.set_id(entry.comment().id());
               t_idf.set_chk(Arrays.toString(md.digest(entry.toString().getBytes("UTF-8"))));
               t_idf.add(entry.toString());
               idf.add(t_idf.cclone());
            }
            
            if(idf.isEmpty()){
                System.out.println("Empty");
            }
            else{
               System.out.println("Feched List"); 
            }
            
            return("Subscriptions Fetched");
        }
        catch(IOException | NullPointerException | NoSuchAlgorithmException ex){
            System.out.println("Couldn't read Subscriptiuon list\n"+ex.getMessage());
            return("Couldn't read Subscriptiuon list");
        }
    }
}
