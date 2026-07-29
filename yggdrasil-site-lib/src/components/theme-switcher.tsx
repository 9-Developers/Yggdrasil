import { type ReactNode, useCallback, useEffect, useState } from "react";

import { AngleDownIcon, ThemeItem } from "~/components";
import {
  DARK_THEME_NAME,
  DEFAULT_THEME_NAMES,
  DEFAULT_THEMES,
  LIGHT_THEME_NAME,
  SYSTEM_THEME,
  SYSTEM_THEME_NAME,
  type Theme,
} from "~/types";

const KEY_THEME = "theme";

export function ThemeSwitcher({
  defaultTheme = SYSTEM_THEME,
  themeNames = DEFAULT_THEME_NAMES,
  themes = DEFAULT_THEMES,
}: Readonly<{
  defaultTheme?: Theme;
  themeNames?: Record<string, string>;
  themes?: readonly Theme[];
}>): ReactNode {
  const savedThemeName = localStorage.getItem(KEY_THEME);
  const savedTheme = themes.find((theme) => savedThemeName === theme.name);
  const [theme, setTheme] = useState(savedTheme ?? defaultTheme);
  const systemTheme = window.matchMedia?.("(prefers-color-scheme: dark)")
    ? DARK_THEME_NAME
    : LIGHT_THEME_NAME;
  const resolvedSystemTheme =
    theme.name === SYSTEM_THEME_NAME ? systemTheme : theme.name;
  const resolvedTheme = themeNames[resolvedSystemTheme] ?? resolvedSystemTheme;

  const saveTheme = useCallback((theme: Readonly<Theme>) => {
    localStorage.setItem(KEY_THEME, theme.name);
    setTheme(theme);
  }, []);

  useEffect(() => {
    document.body.classList.remove(...themes.map((theme) => theme.name));
    document.body.classList.add(resolvedTheme);
  }, [resolvedTheme, themes]);

  return (
    <details>
      <summary>
        <ThemeItem theme={theme} />
        <AngleDownIcon className="expand-icon" />
      </summary>

      <ul>
        {themes.map((theme) => (
          <li key={theme.name} onClick={() => saveTheme(theme)}>
            <ThemeItem theme={theme} />
          </li>
        ))}
      </ul>
    </details>
  );
}
