import type { ReactNode } from "react";

import type { Theme } from "~/types";

export function ThemeItem({ theme }: Readonly<{ theme: Theme }>): ReactNode {
  return (
    <>
      {theme.icon}
      <span>{theme.displayName}</span>
    </>
  );
}
