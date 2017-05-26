/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.controller;

import com.biz.integration.API_AiInterface;
import com.biz.integration.NLPInterface;
import com.biz.model.SkillFlowContext;
import com.biz.model.User;
import com.biz.mongo.MongoDBConnection;
import com.biz.service.DialogServiceImpl;
import com.biz.service.IDialogService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Akhilesh yadav
 */
public class DialogServiceApiController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        MongoDBConnection mongodb = new MongoDBConnection();
        //  User user_bean=new User();
        SkillFlowContext skillFlowContext_bean = new SkillFlowContext();

        Random rand = new Random();
        int pickedNumber = rand.nextInt(100000) + 10000;
        IDialogService obj_dialogservice = new DialogServiceImpl();
        try {
            /* TODO output your page here. You may use following sample code. */
            String userdata = request.getParameter("userdata");

            try {

                JSONObject obj = new JSONObject(userdata);
                HttpSession session = request.getSession();
                String user = "";
                if (session.getAttribute("useremail") != null) {
                    user = session.getAttribute("useremail").toString();
                } else {
                    session.setAttribute("useremail", obj.get("user"));
                    user = session.getAttribute("useremail").toString();
                }
            // get con

                String wType = obj.getString("wType");
                String queryString = obj.getString("queryString");
                skillFlowContext_bean.setSessionId(user);
                skillFlowContext_bean.setContextId(String.valueOf(pickedNumber));
                skillFlowContext_bean.setContextState("progress");
                skillFlowContext_bean.setQueryParameter(queryString);
                skillFlowContext_bean.setCreationDate(new Date());
                skillFlowContext_bean.setNextStep("api.ai");
                //  mongodb.insertContext(skillFlowContext_bean);
                String str = obj_dialogservice.getProgress(skillFlowContext_bean);
                if (str == "NoRecord") {
                    obj_dialogservice.executeSkill(skillFlowContext_bean);
                    System.out.println("inside if");
                } else {
                    System.out.println("inside else");
                    String steps[] = str.split("::");
                    String sessionId = steps[0];
                    String contextId = steps[1];
                    String nextStep = steps[2];
                    String contextState = steps[3];
                    System.out.println("steps 1: " + steps[0]);
                    System.out.println("steps 2: " + steps[1]);
                    System.out.println("steps 2: " + steps[2]);
                    System.out.println("steps 2: " + steps[3]);
                    if (!contextState.equalsIgnoreCase("success") && nextStep.equalsIgnoreCase("api.ai")) {
                        System.out.println("if");
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

                    } else if (!contextState.equalsIgnoreCase("success") && nextStep.equalsIgnoreCase("nlp")) {
                        System.out.println("else");
                        NLPInterface objNLPInterface = new NLPInterface();
                        String nlpres = objNLPInterface.resolveBotRequest(skillFlowContext_bean);
                        System.out.println("nlp_response : " + nlpres);
                        skillFlowContext_bean.setKeyresponse("nlp");
                        skillFlowContext_bean.setValueresponse(nlpres);
                        skillFlowContext_bean.setNextStep("rbac");
                        skillFlowContext_bean.setContextId(contextId);
                        // update updateContext
                        mongodb.updateContext(skillFlowContext_bean);
                    }
                }
       //       System.out.println("str : "+str);
                //  obj_dialogservice.executeSkill(skillFlowContext_bean);
                //       out.println("user :"+user);
                //        out.println("wType :"+wType);
                //       out.println("queryString :"+queryString);

            } catch (JSONException ex) {
                Logger.getLogger(DialogServiceApiController.class.getName()).log(Level.SEVERE, null, ex);
            }
         //   String filearr="hi";

        } finally {
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
