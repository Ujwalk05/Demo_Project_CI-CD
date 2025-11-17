package com.winmore.stepdefinitions.WorkFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class WorkFlow extends PageInitializer {

public static Logger log = LogManager.getLogger(WorkFlow.class);

    @Given("the user clicking on View all workflow Grid")
    public void the_user_clicking_on_button() throws Exception {
        Thread.sleep(4000);
                actionHelper.waitForElementToBeVisible(workFlowPage.viewAllWorkflowGridButton);
                clickHelper.click(workFlowPage.viewAllWorkflowGridButton);
                Log.info("✅ User clicked on View all workflow Grid button successfully");

    }
    @Given("the user running {string} workflow from the view all workflows screen")
    public void the_user_running_workflow_from_the_view_all_workflows_screen(String workflowName) {
        switch (workflowName) {
            case "Third":
                actionHelper.waitForElementToBeVisible(workFlowPage.ClickHere_ThirdWorkflowInWorkflowList);
                clickHelper.click(workFlowPage.ClickHere_ThirdWorkflowInWorkflowList);
                Log.info("✅ User clicked on third workflow in the list successfully");
                break;
            default:
                throw new IllegalArgumentException("Invalid workflow name: " + workflowName);
        }
        actionHelper.waitForElementToBeVisible(workFlowPage.StartWorkflowBtn_InConfirmScreen);
        clickHelper.click(workFlowPage.StartWorkflowBtn_InConfirmScreen);
    }
    
@And("the user verifying that the selected workflow {string} is opened")
public void the_user_verifying_that_the_selected_workflow_is_opened(String workflowName) throws AutomationException {  
    switch (workflowName) {
        case "Sample Workflow RateAI":
            actionHelper.waitForElementToBeVisible(workFlowPage.WorkflowHeader_inWorkflowScreen);
            validationHelper.verifyElementContainsText(workFlowPage.WorkflowHeader_inWorkflowScreen, "Sample Workflow RateAI");
            Log.info("✅ User successfully verified that the selected workflow 'Sample Workflow RateAI' is opened");
            break;
        default:
            throw new IllegalArgumentException("Invalid workflow name: " + workflowName);
    }
}

    @Given("the user selecting the task {string} from task list")
    public void the_user_selecting_the_task_from_task_list(String taskName) {
        String locator="";
        switch (taskName) {
            case "RateAI":
                locator = workFlowPage.Task_RateAI_InTaskList;
                break;

            case "RateAI Adhoc":
                locator = workFlowPage.Task_RateAI_Adhoc_InTaskList;
                break;
            default:
                throw new IllegalArgumentException("Invalid task name: " + taskName);
        }
         actionHelper.waitForElementToBeVisible(locator);
        clickHelper.click(locator);
        Log.info("✅ User clicked on task '" + taskName + "' from task list successfully");
    }

        @Then("the user verifying that the selected task {string} is opened")
        public void the_user_verifying_that_the_selected_task_is_opened(String taskName) throws Exception {
            String locator="";
            switch (taskName) {
                case "RateAI":
                case "RateAI Adhoc":
                    locator = workFlowPage.TaskHeader_inTaskScreen;
                   break;
                default:
                    throw new IllegalArgumentException("Invalid task name: " + taskName);
            }
             actionHelper.waitForElementToBeVisible(locator);
                    validationHelper.verifyElementContainsText(locator, "RateAI");
              Log.info("✅ User successfully verified that the selected task ' " + taskName + " ' is opened");
                                    
            }     
        

        @And("the user selecting the {string} icon")
        public void the_user_selecting_the_icon(String iconName) {
            String locator="";
            switch (iconName) {
                case "Task view close":
                    locator = workFlowPage.TaskViewCloseBtn;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid icon name: " + iconName);
            }
            actionHelper.waitForElementToBeVisible(locator);
            clickHelper.click(locator);
            Log.info("✅ User clicked on '" + iconName + "' icon successfully");
        }

}