APP_NAME=formakoi
REGISTRY=ghcr.io/ggasnier
WEB_IMAGE=$(REGISTRY)/$(APP_NAME)-web:latest
SHELL_IMAGE=$(REGISTRY)/$(APP_NAME)-shell:latest
SSH_HOST=root@ton-vps.fr
SSH_DIR=/srv/apps

.PHONY: build push deploy logs

# 🚀 Build les images localement
build:
	docker build -t $(WEB_IMAGE) ./web
	docker build -t $(SHELL_IMAGE) ./shell

# 📤 Push sur GHCR
push: build
	docker push $(WEB_IMAGE)
	docker push $(SHELL_IMAGE)

# 🔄 Déploiement sur le VPS via SSH
deploy:
	ssh $(SSH_HOST) "cd $(SSH_DIR) && docker compose pull && docker compose up -d"

# 🔍 Logs du web container
logs:
	ssh $(SSH_HOST) "docker logs -f web"
