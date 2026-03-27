package com.guillaumegasnier.education.shell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.security.cert.X509Certificate;

@Configuration
public class RestClientConfig {

    /**
     * SSLContext qui accepte tous les certificats.
     * Nécessaire derrière un proxy d'entreprise qui réalise une inspection TLS
     * avec un certificat auto-signé (PKIX path building failed).
     */
    @Bean
    public SSLContext trustAllSslContext() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Impossible d'initialiser le SSLContext trust-all", e);
        }
    }

    /**
     * Factory qui ouvre une {@link HttpURLConnection} vers n'importe quelle URL
     * en désactivant la vérification SSL (proxy d'entreprise avec certificat auto-signé).
     * Utilisée par {@code ProductionFileService} pour tous les téléchargements directs.
     */
    @Bean
    public TrustAllHttpConnectionFactory trustAllHttpConnectionFactory(SSLContext trustAllSslContext) {
        return url -> {
            HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            if (conn instanceof HttpsURLConnection httpsConn) {
                httpsConn.setSSLSocketFactory(trustAllSslContext.getSocketFactory());
                httpsConn.setHostnameVerifier((hostname, session) -> true);
            }
            return conn;
        };
    }

    @Bean
    public RestClient restClient(SSLContext trustAllSslContext) {
        HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(trustAllSslContext)
                .build();
        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory(httpClient))
                .build();
    }

    /**
     * Interface fonctionnelle pour ouvrir une connexion HTTP(S) préconfigurée SSL.
     */
    @FunctionalInterface
    public interface TrustAllHttpConnectionFactory {
        HttpURLConnection open(String url) throws Exception;
    }
}
