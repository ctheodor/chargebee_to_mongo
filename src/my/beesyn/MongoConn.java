/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.beesyn;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author Christos Theodorakis
 */
public class MongoConn {
    
    private MongoClient mgclnt;
    private MongoDatabase mdb;
    
    public MongoConn(){                                         //Create connection to default server and testdb
        mgclnt = new MongoClient("localhost", 27017);
        mdb = mgclnt.getDatabase("testdb");
    }
    
    public MongoConn(String IP_addr, int Port, String db_name){ //Connect client to a given server and spesific db
        mgclnt = new MongoClient(IP_addr, Port);
        mdb = mgclnt.getDatabase("db_name");
    }
    
    
    public void insert_or_update(Identifier idf, MongoCollection coll){
        Document dc;
        FindOneAndReplaceOptions op = new FindOneAndReplaceOptions();
        op.upsert(true);
        
        dc = Document.parse(idf.get_fetched());
        dc.append("u_id", idf.get_id());
        dc.append("chsum", dc.hashCode());
        coll.findOneAndReplace(Filters.and(Filters.eq("u_id", idf.get_id()), Filters.ne("chsum", idf.get_chk())), dc, op);
    
    }
    
    public void DeleteData(ArrayList <Identifier> idf){
        Iterator<Identifier> iter_idf = idf.iterator();
        MongoCollection coll = null;
        while(iter_idf.hasNext()){
            Identifier t_idf = iter_idf.next();
            coll = this.mdb.getCollection(t_idf.get_cat());
        }
    }
    public MongoCollection GetCollection(String coll_name){      //If collection does not exists create collection
        return(this.mdb.getCollection(coll_name));
    }
    
    public void InsertData(ArrayList <Identifier> idf){
        Iterator<Identifier> iter_idf = idf.iterator();
        MongoCollection coll = null;

        while(iter_idf.hasNext()){  
            Identifier t_idf = iter_idf.next();
            coll = this.mdb.getCollection(t_idf.get_cat());       
            this.insert_or_update(t_idf, coll);
        } 
    }
    
    public void DropColl( String col_name){
        MongoCollection drop_col = this.mdb.getCollection(col_name);
        drop_col.drop();    
    }
}
