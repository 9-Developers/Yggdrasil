import { type ReactNode, useEffect, useState } from "react";
import { Link } from "react-router";

export function Plugins(): ReactNode {
  const [plugins, setPlugins] = useState<string[]>([]);

  useEffect(() => {
    fetch("http://localhost:9999/api/webserver/plugins")
      .then((response) => response.json())
      .then((data) => setPlugins(data as string[]))
      .catch((error) => console.error(error));
  }, []);

  return (
    <>
      <h1>Plugins</h1>

      <ul>
        {plugins.map((plugin) => (
          <li key={plugin}>
            <Link to={plugin}>{plugin}</Link>
          </li>
        ))}
      </ul>
    </>
  );
}
