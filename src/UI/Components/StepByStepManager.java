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
    private Component[] components;
    private int currentStep = 0;
    private boolean isLastStepCompleted = false;
    private Map<Integer, Consumer<Void>> stepShownActions = new HashMap<>();

    public StepByStepManager(Component[] components)
    {
        this.components = components;
        reset();
    }

    public void reset()
    {
        currentStep = 0;
        isLastStepCompleted = false;
        for (Component component : components)
        {
            component.setVisible(false);
        }
        if (components.length > 0)
        {
            components[0].setVisible(true);
        }
    }

    public void nextStep()
    {
        if (currentStep < components.length)
        {
            components[currentStep].setVisible(false);
            currentStep++;
            if (currentStep < components.length)
            {
                components[currentStep].setVisible(true);
            }
        }
    }

    private boolean isLastStepCompleted()
    {
        return isLastStepCompleted;
    }

    public void completeStep(int stepIndex)
    {

        System.out.println("Step " + (stepIndex + 1) + " completed");

        if (stepIndex >= components.length)
        {
            return; // component not found
        }

        // If the component is the current step and it is completed, move to the next step
        if (stepIndex == currentStep)
        {
            isLastStepCompleted = true;
            currentStep++;
            if (currentStep < components.length)
            {
                executeStepShownAction(currentStep);
                components[currentStep].setVisible(true);
            }
        }
        else
        {
            currentStep = stepIndex;
            for (Component component : components)
            {
                component.setVisible(false);
            }

            // show the current step
            currentStep += 2;
            for(int i = 0; i < currentStep && i < components.length; i++)
            {
                components[i].setVisible(true);
            }

            executeStepShownAction(currentStep - 1);
        }
    }

    public void onStepShown(int stepIndex, Consumer<Void> action)
    {
        stepShownActions.put(stepIndex, action);
    }

    private void executeStepShownAction(int stepIndex)
    {
        if (stepShownActions.containsKey(stepIndex))
        {
            stepShownActions.get(stepIndex).accept(null);
        }
    }



}
