package UI.Threads;

import UI.Components.JEnhancedProgressBar;
import UI.Windows.WindowManager;

import javax.swing.*;
import java.awt.*;

public class LoadingThread extends Thread
{
    private final String title;
    private final int duration;
    private final JEnhancedProgressBar progressBar;
    private JFrame frame;
    private Runnable onCompleteAction;
    private boolean isStopped = false;

    public LoadingThread(String title, String message, int duration)
    {
        this.title = title;
        this.duration = duration;
        progressBar = new JEnhancedProgressBar();
        progressBar.setMessage(message);
        progressBar.setPreferredSize(new Dimension(300, 30));
    }

    public void onLoadingComplete(Runnable action)
    {
        this.onCompleteAction = action;
    }

    private void createThreadWindow()
    {
        frame = new JFrame(title);
        frame.setSize(350, 40);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(progressBar);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void run()
    {
        WindowManager.setWindowsEnable(false);
        createThreadWindow();

        float targetProgress = 1.0f;
        long lastTime = System.nanoTime();
        float progress = 0.0f;

        while (progress < targetProgress && !isStopped)
        {
            long currentTime = System.nanoTime();
            float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
            lastTime = currentTime;

            float increment = deltaTime / (duration / 1000.0f);

            progress = Math.min(targetProgress, progress + increment);
            progressBar.setProgress(progress);

            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                WindowManager.setWindowsEnable(true);
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        SwingUtilities.invokeLater(() ->
        {
            frame.dispose();
            WindowManager.setWindowsEnable(true);
            if(onCompleteAction != null && !isStopped)
            {
                onCompleteAction.run();
            }
        });
    }

    public void stopLoading()
    {
        isStopped = true;
    }


}
