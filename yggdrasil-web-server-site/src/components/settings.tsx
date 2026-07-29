import { type ReactNode } from "react";
import { Link } from "react-router";
import { DiscordIcon, GitLabIcon, ThemeSwitcher } from "yggdrasil-web-lib";

export function Settings(): ReactNode {
  return (
    <div className="settings">
      <Link to="https://discord.gg/KTgshSbEUU" target="_blank">
        <DiscordIcon />
      </Link>

      <Link to="https://codeberg.org/9-Developers/Yggdrasil" target="_blank">
        <GitLabIcon />
      </Link>

      <ThemeSwitcher />
    </div>
  );
}
