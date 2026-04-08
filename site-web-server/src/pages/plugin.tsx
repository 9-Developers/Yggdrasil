import { type ReactNode, useEffect, useState } from "react";
import { Link, useParams } from "react-router";

import { type PluginResponse } from "~/types";

export function Plugin(): ReactNode {
  const [registeredPlugin, setRegisteredPlugin] = useState<PluginResponse>();
  const { plugin } = useParams();

  useEffect(() => {
    fetch(`http://localhost:9999/api/webserver/plugins/${plugin}`)
      .then((response) => response.json())
      .then((data) => setRegisteredPlugin(data as PluginResponse))
      .catch((error) => console.error(error));
  }, [plugin]);

  return (
    <>
      <h1>{plugin}</h1>

      <h2>HTTP paths</h2>

      <ul>
        {registeredPlugin?.paths.map((path) => (
          <li key={path}>
            <Link to={path}>{path}</Link>
          </li>
        ))}
      </ul>

      <h2>Resources</h2>

      <ul>
        {registeredPlugin?.resources.map((resource) => (
          <li key={resource}>
            <Link to={`/webserver/status/${plugin}/${resource}`}>
              {resource}
            </Link>
          </li>
        ))}
      </ul>
    </>
  );
}
