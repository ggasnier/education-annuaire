docker run --rm -it \
-v $(pwd)/certs:/etc/letsencrypt \
-v $(pwd)/nginx.conf:/etc/nginx/nginx.conf:ro \
certbot/certbot certonly --webroot -w /var/www/html \
-d ton-domaine.fr --email ton.email@domaine.fr --agree-tos --non-interactive
