package com.winmore.pages;

public class WorkFlowPage {
    

    public String viewAllWorkflowGridButton = "//span[@class='-u-btn__content -u-btn__content--pulse-grow']//i[@class='-u-icon -u-icon--all -u-flex--none'] | //div[@class='navigationsection__content']//i[@class='-u-icon -u-icon--all']";
    public String viewAllWorkflowGridButton_Description = "Button to view all workflow grid";

    public String ClickHere_ThirdWorkflowInWorkflowList = "//ul[@class='workflows-dashboard__list -u-flex']//li[@class='workflows-dashboard__cell workflows-dashboard__cell--launchable'][3]//b[contains(text(),'Click here')]";    
    public String ClickHere_ThirdWorkflowInWorkflowList_Description = "Link to click on third workflow in the list";

    public String StartWorkflowBtn_InConfirmScreen = "//div[@class='start-workflow']//button[contains(text(),'Start')] | //div[@class='confirm-slideout']//button[contains(text(),'Start')]";
    public String StartWorkflowBtn_InConfirmScreen_Description = "Button to start the workflow in confirm screen";

    public String WorkflowHeader_inWorkflowScreen= "//h2[@class='pageheader__title']";
    public String WorkflowHeader_inWorkflowScreen_Description= "Header in the workflow screen";

    public String Task_RateAI_InTaskList= "//div[@class='stage-detail']//div[contains(text(),'RateAI')]";
    public String Task_RateAI_InTaskList_Description= "Task RateAI in the task list";

    public String TaskHeader_inTaskScreen= "//div[@class='client-components-task-components-__title-module__taskName']";
    public String TaskHeader_inTaskScreen_Description= "Header in the task screen";


}

