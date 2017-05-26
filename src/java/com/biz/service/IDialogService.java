/**
 * 
 */
package com.biz.service;

import com.biz.model.SkillFlowContext;

/**
 * @author akhilesh yadav
 *
 */
public interface IDialogService {
    
   public String executeSkill(SkillFlowContext skillFlowContext);
   public String getProgress(SkillFlowContext skillFlowContext);
  // public TaskFlow requestTaskFlowParameters(TaskFlow taskFlow);
   
   
   public String configureSkill();
   
   public void notifyCompletion();
}
