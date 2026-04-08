import { type ReactNode, useCallback, useEffect, useState } from "react";

import {
  AngleDownIcon,
  ThemeAutoIcon,
  ThemeFrappeIcon,
  ThemeLatteIcon,
  ThemeMacchiatoIcon,
  ThemeMochaIcon,
} from "~/components";

function getSummary(theme?: Readonly<string>): ReactNode {
  let icon: ReactNode;
  let label: string;

  if ("latte" === theme) {
    icon = <ThemeLatteIcon />;
    label = "Latte (Light)";
  } else if ("frappe" === theme) {
    icon = <ThemeFrappeIcon />;
    label = "Frappé";
  } else if ("macchiato" === theme) {
    icon = <ThemeMacchiatoIcon />;
    label = "Macchiato";
  } else if ("mocha" === theme) {
    icon = <ThemeMochaIcon />;
    label = "Mocha (Dark)";
  } else {
    icon = <ThemeAutoIcon />;
    label = "System";
  }

  return (
    <>
      {icon}
      <span>{label}</span>
    </>
  );
}

const KEY_THEME = "theme";
const THEMES = ["latte", "frappe", "macchiato", "mocha"];
const THEME_NAMES: Record<string, string> = { "dark": "mocha", "light": "latte" };

export function ThemeSwitcher(): ReactNode {
  const savedTheme = localStorage.getItem(KEY_THEME);
  const [theme, setTheme] = useState(savedTheme ?? "system");
  const systemTheme = window.matchMedia?.("(prefers-color-scheme: dark)")
    ? "dark"
    : "light";
  const resolvedSystemTheme = theme === "system" ? systemTheme : theme;
  const resolvedTheme: string = THEME_NAMES[resolvedSystemTheme] ?? resolvedSystemTheme;

  const saveTheme = useCallback((theme: Readonly<string>) => {
    localStorage.setItem(KEY_THEME, theme);
    setTheme(theme);
  },
    []);

  useEffect(() => {
    document.body.classList.remove(...THEMES);
    document.body.classList.add(resolvedTheme);
  }, [resolvedTheme]);

  return (
    <details>
      <summary>
        {getSummary(theme)}
        <AngleDownIcon className="expand-icon" />
      </summary>

      <ul>
        <li onClick={() => saveTheme("system")}>{getSummary("system")}</li>
        <li onClick={() => saveTheme("latte")}>{getSummary("latte")}</li>
        <li onClick={() => saveTheme("frappe")}>{getSummary("frappe")}</li>
        <li onClick={() => saveTheme("macchiato")}>{getSummary("macchiato")}</li>
        <li onClick={() => saveTheme("mocha")}>{getSummary("mocha")}</li>
      </ul>
    </details>
  );
}
