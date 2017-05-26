/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biz.mongo;

/**
 *
 * @author Akhilesh yadav
 */
import com.biz.model.SkillFlowContext;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;
public class MongoDBConnection {
    public static Mongo mongo;
    public static DB db;
    public static DBCollection context = null;
    DBCursor context_cursor;
    

    public MongoDBConnection() {

        try {
            mongo = new Mongo("localhost", 27017);
            db = mongo.getDB("dialogservice");
            context = db.getCollection("context");
            System.out.println("Return collection object.......");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String insertContext(SkillFlowContext skillFlowContext){
    BasicDBObject doc = new BasicDBObject("sessionId", skillFlowContext.getSessionId()).
            append("contextId", skillFlowContext.getContextId()).
            append("contextState", skillFlowContext.getContextState()).
            append("queryParameter", skillFlowContext.getQueryParameter()).
            append("creationDate", SimpleDateFormatExample.getDateFormat().format(skillFlowContext.getCreationDate())).
            append("nextStep",skillFlowContext.getNextStep());
				
         context.insert(doc);
         return skillFlowContext.getContextId();
    }
    public String updateContext(SkillFlowContext skillFlowContext){
//    context.findAndModify(new BasicDBObject("sessionId", skillFlowContext.getSessionId()),new BasicDBObject("contextId", skillFlowContext.getContextId()),
//     new BasicDBObject("$push", new BasicDBObject("nameIdentity", new BasicDBObject("fName", "123").append("lName", "456")
//    .append("dob", "00").append("address", "789"))));
//        return "done";
        System.out.println("in siide updateContext : updateContext");
        System.out.println("skillFlowContext.getSessionId() : "+skillFlowContext.getSessionId());
        context.findAndModify(new BasicDBObject("sessionId", skillFlowContext.getSessionId()),new BasicDBObject("contextId", skillFlowContext.getContextId()),
     new BasicDBObject("$push", new BasicDBObject(skillFlowContext.getKeyresponse(),skillFlowContext.getValueresponse())));
       
        BasicDBObject newDocument = new BasicDBObject();
        System.out.println("skillFlowContext.getSessionId() : "+skillFlowContext.getNextStep());
        newDocument.append("$set", new BasicDBObject().append("nextStep", skillFlowContext.getNextStep()));
      //  newDocument.append("$set", new BasicDBObject().append("FileJson", jsonobject));

        BasicDBObject searchQuery = new BasicDBObject().append("contextId" , skillFlowContext.getContextId());

        context.update(searchQuery, newDocument);
        return "done";
    }
    
     public String getMongoDBContext(SkillFlowContext skillFlowContext){
                DBObject clause_sessionId = new BasicDBObject("sessionId", skillFlowContext.getSessionId());
               // DBObject clause_contextId = new BasicDBObject("contextId", skillFlowContext.getContextId());
               // DBObject clause_contextId = new BasicDBObject("queryParameter", java.util.regex.Pattern.compile(skillFlowContext.getQueryParameter()));
                DBObject clause_contextId = new BasicDBObject("queryParameter", skillFlowContext.getQueryParameter());
                BasicDBList and_query = new BasicDBList();
                and_query.add(clause_sessionId);
                and_query.add(clause_contextId);
                String contextId=null;
                String nextStep=null;
                
                DBObject query_text = new BasicDBObject("$and", and_query);
                context_cursor = context.find(query_text);
                System.out.println("context_cursor "+context_cursor);
                System.out.println("context_cursor.hasNext() "+context_cursor.hasNext());
                while(context_cursor.hasNext()){
                    System.out.println("step 1");
                    DBObject db_obj=context_cursor.next();
                contextId=db_obj.get("sessionId").toString();
                System.out.println("contextId "+contextId);
                nextStep=db_obj.get("nextStep").toString();
                System.out.println("nextStep "+nextStep);
                }
                System.out.println("contextId+\"::\"+nextStep : "+contextId+"::"+nextStep);
              return contextId+"::"+nextStep;
     }
     public String getProgressStatus(SkillFlowContext skillFlowContext){
                DBObject clause_sessionId = new BasicDBObject("sessionId", skillFlowContext.getSessionId());
               // DBObject clause_contextId = new BasicDBObject("contextId", skillFlowContext.getContextId());
               // DBObject clause_contextId = new BasicDBObject("queryParameter", java.util.regex.Pattern.compile(skillFlowContext.getQueryParameter()));
                DBObject clause_contextId = new BasicDBObject("queryParameter", skillFlowContext.getQueryParameter());
                BasicDBList and_query = new BasicDBList();
                and_query.add(clause_sessionId);
                and_query.add(clause_contextId);
                String sessionId=null;
                String contextId=null;
                String nextStep=null;
                String contextState=null;
                String progress_result=null;
                
                DBObject query_text = new BasicDBObject("$and", and_query);
                context_cursor = context.find(query_text);
            //    System.out.println("context_cursor "+context_cursor);
            //    System.out.println("context_cursor.hasNext() "+context_cursor.hasNext());
                if(context_cursor.hasNext()){
                    while(context_cursor.hasNext()){
                        System.out.println("step 1");
                        DBObject db_obj=context_cursor.next();
                            sessionId=db_obj.get("sessionId").toString();
             //               System.out.println("sessionId "+sessionId);
                            
                            contextId=db_obj.get("contextId").toString();
                //            System.out.println("contextId "+contextId);
                            
                            nextStep=db_obj.get("nextStep").toString();
                //            System.out.println("nextStep "+nextStep);
                            
                            contextState=db_obj.get("contextState").toString();
                 //           System.out.println("contextState "+contextState);
                            
                  //          System.out.println("sessionId::contextId::nextStep::contextState "+sessionId+"::"+contextId+"::"+nextStep+"::"+contextState);
                         progress_result=sessionId+"::"+contextId+"::"+nextStep+"::"+contextState;
                    }
                }else{
                    progress_result="NoRecord";
                }
                return progress_result;
     }
    
}
