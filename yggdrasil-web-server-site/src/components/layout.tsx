import { type ReactNode } from "react";
import { Outlet } from "react-router";

import { Header } from "~/components";

export function Layout(): ReactNode {
  return (
    <>
      <Header />

      <main>
        <Outlet />
      </main>
    </>
  );
}
