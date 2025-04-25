-- salted passwords
UPDATE users
SET password = '$2a$10$8LMwQJbP0zR6c.y/RD/ErOwHqPLH2AU.2GwBCg0C3qKBMPWbJgBQm'   -- SecurePass123
WHERE id = 1;
UPDATE users
SET password = '$2a$10$6iA/yn/TjHx8nmBi7bmKF.8Zszc5rcPX/uzmsOhG3psbu/SRcyffK'   -- StrongPassword456
WHERE id = 2;
UPDATE users
SET password = '$2a$10$jLbZoTFDijBbHRiHyXxom.HqNBQxpjaXeokG87w0pUEn2KT35FnZC'   -- SafePass789
WHERE id = 3;
UPDATE users
SET password = '$2a$10$U5wtL468PcEz148jVNeO3uqyHbAPstAY2Z.IHDiv/.LQWbeMdEVIe'   -- ProtectedPass012
WHERE id = 4;
UPDATE users
SET password = '$2a$10$8BsmIbP/S2XYZn0Eh2WpxeM40BVAJj.MTbX9jH/sZMKa7MdsJEbYO'   -- SecretPass345
WHERE id = 5;