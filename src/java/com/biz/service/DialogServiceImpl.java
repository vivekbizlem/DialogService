/**
 * 
 */
package com.biz.service;

import com.biz.integration.API_AiInterface;
import com.biz.integration.NLPInterface;
import com.biz.model.SkillFlowContext;
import com.biz.mongo.MongoDBConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



/**
 * @author akhilesh yadav
 *
 */

public class DialogServiceImpl implements IDialogService{
          MongoDBConnection mongodb=new MongoDBConnection();
       //   SkillFlowContext skillFlowContext_bean=new SkillFlowContext();
	@Override
	public String executeSkill(SkillFlowContext skillFlowContext_bean) {
		// TODO Auto-generated method stub
          
            String contextId = mongodb.insertContext(skillFlowContext_bean);
            skillFlowContext_bean.setContextId(contextId);
            API_AiInterface ai_interface = new API_AiInterface();
              String ai_response = ai_interface.resolveApiAiRequest(skillFlowContext_bean);
              System.out.println("ai_response : " + ai_response);
//                  skillFlowContext_bean.setApi_aiResponse(ai_response);
              skillFlowContext_bean.setKeyresponse("api_ai");
              skillFlowContext_bean.setValueresponse(ai_response);
              skillFlowContext_bean.setNextStep("nlp");
              skillFlowContext_bean.setContextId(contextId);
                        // update updateContext
              mongodb.updateContext(skillFlowContext_bean);
              
              NLPInterface objNLPInterface = new NLPInterface();
                        String nlpres = objNLPInterface.resolveBotRequest(skillFlowContext_bean);
                        System.out.println("nlp_response : " + nlpres);
                        skillFlowContext_bean.setKeyresponse("nlp");
                        skillFlowContext_bean.setValueresponse(nlpres);
                        skillFlowContext_bean.setNextStep("rbac");
                        skillFlowContext_bean.setContextId(contextId);
                        // update updateContext
                        mongodb.updateContext(skillFlowContext_bean);
           // String contextId="No Contex";
           // String updateContext = mongodb.updateContext(skillFlowContext);
          //  System.out.println("updateContext : "+updateContext);
        //    String getContext = mongodb.getMongoDBContext(skillFlowContext);
        //    System.out.println("getContext : "+getContext);
            // set context id
            
            // call to api.ai
            
            // call to nlp 
            
            // call to rbac
            
            
            return contextId;
	}

	@Override
	public String getProgress(SkillFlowContext skillFlowContext) {
		// TODO Auto-generated method stub
                String progress_status= mongodb.getProgressStatus(skillFlowContext);
		return progress_status;
	}

//	@Override
//	public TaskFlow requestTaskFlowParameters(TaskFlow taskFlow) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String configureSkill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyCompletion() {
		// TODO Auto-generated method stub
		
	}
     
}