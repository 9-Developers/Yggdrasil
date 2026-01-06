import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router";

import "./index.css";

import Layout from "~/components/layout";
import Home from "~/pages/home";
import Plugin from "~/pages/plugin";
import Plugins from "~/pages/plugins";
import Resources from "~/pages/resources";
import Status from "~/pages/status";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/webserver" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="plugins" element={<Plugins />} />
          <Route path="plugins/:plugin" element={<Plugin />} />
          <Route path="resources" element={<Resources />} />
          <Route path="status" element={<Status />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </StrictMode>,
);
