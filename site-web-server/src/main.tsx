import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router";

import "./index.css";

import { Layout } from "~/components";
import { Home, Plugin, Plugins, Resources, Status } from "~/pages";

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
