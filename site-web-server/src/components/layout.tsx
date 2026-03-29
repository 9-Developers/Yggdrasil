import type { ReactNode } from "react";
import { Outlet } from "react-router";

import Header from "~/components/header";

function Layout(): ReactNode {
  return (
    <>
      <Header />

      <main>
        <Outlet />
      </main>
    </>
  );
}

export default Layout;
