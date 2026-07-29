import type { ReactNode } from "react";

import {
  ThemeAutoIcon,
  ThemeFrappeIcon,
  ThemeLatteIcon,
  ThemeMacchiatoIcon,
  ThemeMochaIcon,
} from "~/components";

/**
 * Theme information.
 */
export interface Theme {
  /** Formatted name for displaying on the UI. */
  readonly displayName: string;
  /** Theme icon. */
  readonly icon?: ReactNode;
  /** Theme class name for adding to `body` tag and saving to local storage. */
  readonly name: string;
}

/**
 * Map theme names to different class names.
 *
 * By default dark is mapped to Catppuccin Mocha and light is mapped to
 * Catppuccin Latte.
 */
export const DEFAULT_THEME_NAMES: Record<string, string> = {
  dark: "mocha",
  light: "latte",
};

/**
 * Dark theme name.
 */
export const DARK_THEME_NAME = "dark";

/**
 * Catppuccin Latte theme.
 */
export const LATTE_THEME: Readonly<Theme> = {
  displayName: "Latte (Light)",
  icon: <ThemeLatteIcon />,
  name: "latte",
};

/**
 * Light theme name.
 */
export const LIGHT_THEME_NAME = "light";

/**
 * Catppuccin Frappé theme.
 */
export const FRAPPE_THEME: Readonly<Theme> = {
  displayName: "Frappé",
  icon: <ThemeFrappeIcon />,
  name: "frappe",
};

/**
 * Catppuccin Macchiato theme.
 */
export const MACCHIATO_THEME: Readonly<Theme> = {
  displayName: "Macchiato",
  icon: <ThemeMacchiatoIcon />,
  name: "macchiato",
};

/**
 * Catppuccin Mocha theme.
 */
export const MOCHA_THEME: Readonly<Theme> = {
  displayName: "Mocha (Dark)",
  icon: <ThemeMochaIcon />,
  name: "mocha",
};

/**
 * Automatic/system theme.
 */
export const SYSTEM_THEME: Readonly<Theme> = {
  displayName: "System",
  icon: <ThemeAutoIcon />,
  name: "system",
};

/**
 * Automatic/system theme name.
 */
export const SYSTEM_THEME_NAME = "system";

/**
 * Default supported themes:
 *
 * - `system`: Automatic/system theme
 * - `latte`: Catppuccin Latte (light theme)
 * - `frappe`: Catppuccin Frappé
 * - `macchiato`: Catppuccin Macchiato
 * - `mocha`: Catppuccin Mocha (dark theme)
 */
export const DEFAULT_THEMES: readonly Theme[] = [
  SYSTEM_THEME,
  LATTE_THEME,
  FRAPPE_THEME,
  MACCHIATO_THEME,
  MOCHA_THEME,
];
