package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// this class will only be used to manage the step by step logic
// Display will not be handled here
public class StepByStepManager
{
    private final Component[] components;
    private int currentStep = 0;
    private boolean isStopCalled = false;
    private final Map<Integer, Runnable> stepShownActions = new HashMap<>();

    /**
     * Constructor
     *
     * @param components the components to manage and use by the step by step logic
     */
    public StepByStepManager(Component[] components)
    {
        this.components = components;
        reset();
    }

    /**
     * Reset the step by step manager to the first step
     */
    public void reset()
    {
        currentStep = 0;
        for (Component component : components)
        {
            component.setVisible(false);
        }
        if (components.length > 0)
        {
            components[0].setVisible(true);
        }
    }

    /**
     * Get the current step index
     *
     * @return the current step index
     */
    public int getCurrentStep()
    {
        return currentStep;
    }

    /**
     * Stop the step by step manager. This will hide all steps and stop the logic
     */
    public void stop()
    {
        isStopCalled = true;
    }

    /**
     * Complete a step and move to the next step. If the step index is lower than the current step, all steps between step index and current step will be hidden
     *
     * @param stepIndex the index of the step to complete. can be a step already completed
     */
    public void completeStep(int stepIndex)
    {
        System.out.println("Step " + (stepIndex + 1) + " completed");

        if (stepIndex >= components.length) return;


        // if the step to complete is the current step, then we need to move to the next step
        // otherwise we need to hide all steps after the 'stepIndex'
        if (stepIndex == currentStep)
        {
            currentStep++;
            if (currentStep < components.length)
            {
                // trigger the next step
                executeStepShownAction(currentStep);

                if(!isStopCalled)
                {
                    components[currentStep].setVisible(true);
                }
                else
                {
                    currentStep--;
                }
            }
        }
        else
        {
            currentStep = stepIndex;

            // hide all steps after the 'stepIndex'
            int i = currentStep + 1;
            while(i < components.length && components[i].isVisible())
            {
                components[i].setVisible(false);
                i++;
            }

            // complete the current step to trigger the next step
            completeStep(currentStep);
        }
    }

    /**
     * Callback when a step is shown. This will be called when the previous step is completed
     *
     * @param stepIndex the index of the step to show
     * @param action    the action to execute when the step is shown
     */
    public void onStepShown(int stepIndex, Runnable action)
    {
        stepShownActions.put(stepIndex, action);
    }

    private void executeStepShownAction(int stepIndex)
    {
        if (stepShownActions.containsKey(stepIndex))
        {
            stepShownActions.get(stepIndex).run();
        }
    }



}
