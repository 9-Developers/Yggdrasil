import { type ReactNode, useEffect, useState } from "react";

import { PluginResources } from "~/components";

export function Resources(): ReactNode {
  const [resources, setResources] = useState<Record<string, string[]>>({});

  useEffect(() => {
    fetch("http://localhost:9999/api/webserver/resources")
      .then((response) => response.json())
      .then((data) => setResources(data as Record<string, string[]>))
      .catch((error) => console.error(error));
  }, []);

  return (
    <>
      <h1>Registered resources</h1>

      {Object.keys(resources).map((plugin) => (
        <PluginResources
          key={plugin}
          plugin={plugin}
          resources={resources[plugin]}
        />
      ))}
    </>
  );
}
