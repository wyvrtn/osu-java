/**
 * This package holds the utility components of this API wrapper.
 * <p>
 * This package contains two <i>Data Transfer Objects</i>(DTOs),
 * {@link HttpClientCreator RequestBundle} and {@link HttpRequestProperties RequestProperties}.
 * The former contains a {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient CloseableHttpClient} and
 * an instance of RequestProperties, while the latter is just a simple DTO
 * containing some configuration information used for the CloseableHttpClient
 * in RequestBundle. A convenience object {@link HttpRequest HttpRequest}, used
 * along with {@link HttpMethod HttpMethod} is provided for HTTP requests.
 * Some data conversion methods are provided in <b>interface</b> {@link NetIOUtilities NetIOUtilities},
 * also for convenience.
 *
 * </p>
 *
 * @since 1.0.0
 * @author wyvrtn
 * @version 1.0.1
 */
package jospi.client.request;
