/* eslint @typescript-eslint/no-explicit-any: "off", @typescript-eslint/no-unsafe-return: "off"  */
import { type CleanedWhere, createAdapterFactory } from "better-auth/adapters";

export const ninecraftAdapter = () =>
  createAdapterFactory({
    config: {
      adapterId: "ninecraft-adapter",
      adapterName: "Ninecraft",
      debugLogs: false,
      supportsJSON: true,
      supportsDates: true,
      supportsBooleans: true,
      supportsNumericIds: true,
      usePlural: true,
    },
    adapter: () => ({
      count: async ({ model, where }) =>
        await fetch(`http://localhost:9999/api/auth/${model}`, {
          method: "GET",
          body: JSON.stringify({
            where,
          }),
        }).then((response) => Number(response.text())),
      create: async <T extends Record<string, any>>({
        data,
        model,
        select,
      }: Readonly<{ data: T; model: string; select?: string[] | undefined }>) =>
        await fetch(`http://localhost:9999/api/auth/${model}`, {
          method: "POST",
          body: JSON.stringify({
            data,
            select,
          }),
        }).then((response) => response.json() as unknown as T),
      delete: async ({ model, where }) => {
        await fetch("http://localhost:9999/api/auth", {
          method: "DELETE",
          body: JSON.stringify({
            model,
            where,
          }),
        });
      },
      deleteMany: async ({ model, where }) =>
        await fetch("http://localhost:9999/api/auth", {
          method: "DELETE",
          body: JSON.stringify({
            model,
            where,
          }),
        }).then((response) => Number(response.text())),
      findMany: async ({ join, limit, model, offset, sortBy, where }) =>
        await fetch("http://localhost:9999/api/auth", {
          method: "PUT",
          body: JSON.stringify({
            join,
            limit,
            model,
            offset,
            sortBy,
            where,
          }),
        }).then((response) => response.json()),
      findOne: async ({ join, model, select, where }): Promise<any> =>
        await fetch("http://localhost:9999/api/auth", {
          method: "PUT",
          body: JSON.stringify({
            join,
            model,
            select,
            where,
          }),
        }).then((response) => response.json()),
      update: async <T>({
        model,
        update,
        where,
      }: Readonly<{ model: string; update: T; where: CleanedWhere[] }>) =>
        await fetch("http://localhost:9999/api/auth", {
          method: "POST",
          body: JSON.stringify({
            model,
            update,
            where,
          }),
        }).then((response) => response.json() as unknown as T),
      updateMany: async ({ model, update, where }) =>
        await fetch("http://localhost:9999/api/auth", {
          method: "POST",
          body: JSON.stringify({
            model,
            update,
            where,
          }),
        }).then((response) => Number(response.text())),
    }),
  });
