package UI.Theme;

import java.util.Properties;

@FunctionalInterface
public interface ThemeObserver
{
    void onThemeChanged(Properties themeProperties);
}
