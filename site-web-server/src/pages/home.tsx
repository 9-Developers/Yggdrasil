import { type ReactNode, useEffect, useState } from "react";

import PluginPaths from "~/components/plugin-paths";

function Home(): ReactNode {
  const [paths, setPaths] = useState<Record<string, string[]>>({});

  useEffect(() => {
    fetch("http://localhost:9999/api/webserver/paths")
      .then((response) => response.json())
      .then((data) => setPaths(data as Record<string, string[]>))
      .catch((error) => console.error(error));
  }, []);

  return (
    <>
      <h1>Registered paths</h1>

      {Object.keys(paths).map((plugin) => (
        <PluginPaths key={plugin} paths={paths[plugin]} plugin={plugin} />
      ))}
    </>
  );
}

export default Home;
