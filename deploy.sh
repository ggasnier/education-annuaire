#!/bin/bash
set -e

APP_DIR=/srv/apps

echo "➡️ Mise à jour du projet sur le serveur..."
cd $APP_DIR

echo "📥 Pull des dernières images..."
docker compose pull

echo "🔄 Restart des containers..."
docker compose up -d

echo "✅ Déploiement terminé !"
