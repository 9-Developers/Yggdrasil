import type { ReactNode } from "react";
import { NavLink } from "react-router";

import { Settings } from "~/components";

export function Header(): ReactNode {
  return (
    <header>
      <h1>9craft Web Server</h1>

      <nav>
        <NavLink
          className={({ isActive }) =>
            isActive ? "active nav-link" : "nav-link"
          }
          end
          to="/webserver"
        >
          Home
        </NavLink>
        <NavLink
          className={({ isActive }) =>
            isActive ? "active nav-link" : "nav-link"
          }
          to="/webserver/plugins"
        >
          Plugins
        </NavLink>
        <NavLink
          className={({ isActive }) =>
            isActive ? "active nav-link" : "nav-link"
          }
          to="/webserver/resources"
        >
          Resources
        </NavLink>
        <NavLink
          className={({ isActive }) =>
            isActive ? "active nav-link" : "nav-link"
          }
          to="/webserver/status"
        >
          Status
        </NavLink>
      </nav>

      <Settings />
    </header>
  );
}
