package com.guillaumegasnier.education.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeoController {

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String robots(HttpServletRequest request) {
//        String base = getBaseUrl(request);
        StringBuilder sb = new StringBuilder();
        sb.append("User-agent: *\n");
        sb.append("Allow: /\n");
//        sb.append("Sitemap: ").append(base).append("/sitemap.xml\n");
        return sb.toString();
    }

//    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
//    @ResponseBody
//    public String sitemapIndex(HttpServletRequest request) {
//        String base = getBaseUrl(request);
//        List<String> sitemaps = new ArrayList<>();
//        sitemaps.add(base + "/sitemap-static.xml");
//        sitemaps.add(base + "/sitemap-etablissements.xml");
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//        sb.append("<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
//        for (String sm : sitemaps) {
//            sb.append("  <sitemap>\n");
//            sb.append("    <loc>").append(escapeXml(sm)).append("</loc>\n");
//            sb.append("  </sitemap>\n");
//        }
//        sb.append("</sitemapindex>");
//        return sb.toString();
//    }
//
//    @GetMapping(value = "/sitemap-static.xml", produces = MediaType.APPLICATION_XML_VALUE)
//    @ResponseBody
//    public String sitemapStatic(HttpServletRequest request) {
//        String base = getBaseUrl(request);
//        // generate a small static sitemap (home, mentions)
//        StringBuilder sb = new StringBuilder();
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
//        sb.append(urlEntry(base + "/", "daily", "1.0"));
//        sb.append(urlEntry(base + "/mentions-legales", "monthly", "0.5"));
//        sb.append("</urlset>");
//        return sb.toString();
//    }
//
//    @GetMapping(value = "/sitemap-etablissements.xml", produces = MediaType.APPLICATION_XML_VALUE)
//    @ResponseBody
//    public String sitemapEtablissements(HttpServletRequest request) {
//        String base = getBaseUrl(request);
//        // Placeholder: in future this should be generated from DB (paginated)
//        StringBuilder sb = new StringBuilder();
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
//        // Example placeholder entry
//        sb.append(urlEntry(base + "/", "weekly", "0.8"));
//        sb.append("</urlset>");
//        return sb.toString();
//    }
//
//    // helpers
//    private String urlEntry(String loc, String changefreq, String priority) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("  <url>\n");
//        sb.append("    <loc>").append(escapeXml(loc)).append("</loc>\n");
//        sb.append("    <changefreq>").append(escapeXml(changefreq)).append("</changefreq>\n");
//        sb.append("    <priority>").append(escapeXml(priority)).append("</priority>\n");
//        sb.append("  </url>\n");
//        return sb.toString();
//    }
//
//    private String getBaseUrl(HttpServletRequest request) {
//        String scheme = request.getScheme(); // http or https
//        String serverName = request.getServerName();
//        int port = request.getServerPort();
//        boolean isDefaultPort = (scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443);
//        String context = request.getContextPath() == null ? "" : request.getContextPath();
//        return URI.create(scheme + "://" + serverName + (isDefaultPort ? "" : ":" + port) + context).toString();
//    }
//
//    private String escapeXml(String s) {
//        if (s == null) return "";
//        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
//    }
}
