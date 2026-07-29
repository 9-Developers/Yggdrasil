/**
 * Resource status code.
 */
export type StatusCode = "OK" | "WARNING" | "ERROR";

/**
 * Resource status.
 */
export type Status = Readonly<{
  /** Status code. */
  code: StatusCode;
  /** Overall status message. */
  message: string;
  /** Status error messages. */
  errorsMessages: readonly string[];
  /** Status warning messages. */
  warningMessages: readonly string[];
  /** Status information messages. */
  infoMessages: readonly string[];
}>;

/**
 * Resource statuses grouped by code.
 */
export type StatusResponse = Readonly<{
  /** Error statuses. */
  errors: Record<string, Status>;
  /** Warning statuses. */
  warnings: Record<string, Status>;
  /** Ok statuses. */
  oks: Record<string, Status>;
}>;
