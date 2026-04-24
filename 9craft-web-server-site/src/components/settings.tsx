import { DiscordIcon, GitLabIcon, ThemeSwitcher } from "9craft-web-lib";
import { type ReactNode } from "react";
import { Link } from "react-router";

export function Settings(): ReactNode {
  return (
    <div className="settings">
      <Link to="https://discord.gg/KTgshSbEUU" target="_blank">
        <DiscordIcon />
      </Link>

      <Link to="https://gitlab.com/9developers/9craft" target="_blank">
        <GitLabIcon />
      </Link>

      <ThemeSwitcher />
    </div>
  );
}
