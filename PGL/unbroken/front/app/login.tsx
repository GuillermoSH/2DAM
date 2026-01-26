import React, { useState } from "react";
import {
  View,
  Text,
  TextInput,
  Pressable,
  StyleSheet,
  Alert,
  ActivityIndicator,
} from "react-native";
import { useRouter } from "expo-router";
import { loginUser, registerUser } from "./api";

export default function LoginScreen() {
  const router = useRouter();
  const [alias, setAlias] = useState("");
  const [password, setPassword] = useState("");
  const [mode, setMode] = useState<"login" | "register">("login");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleAuth = async () => {
    setError("");
    if (!alias || !password) {
      Alert.alert("Error", "Completa alias y contraseña");
      return;
    }

    let error = "La contraseña debe:\n";
    if (password.length < 6) error += "- Tener al menos 6 caracteres\n";
    if (!password.match(/[A-Z]/)) error += "- Incluir al menos una mayúscula\n";
    if (!password.match(/[a-z]/)) error += "- Incluir al menos una minúscula\n";
    if (!password.match(/\d/)) error += "- Incluir al menos un número\n";
    if (!password.match(/[.$%_?@]/)) error += "- Incluir al menos un símbolo (.$%_?@)\n";

    if (error != "La contraseña debe:\n") {
      setError(error);
      Alert.alert("Error", error);
      return;
    }

    setLoading(true);
    
    try {
      if (mode === "login") {
        await loginUser(alias, password);
      } else {
        const data = await registerUser(alias, password);
        if (data.msg === "Usuario registrado") await loginUser(alias, password);
      }
      router.replace("/");
    } catch (e: any) {
      Alert.alert("Error", e.message || "Falló la solicitud");
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        {mode === "login" ? "Iniciar sesión" : "Crear cuenta"}
      </Text>
      <TextInput
        placeholder="Alias"
        value={alias}
        onChangeText={(e) => setAlias(e)}
        style={styles.input}
        autoCapitalize="none"
      />
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={(e) => setPassword(e)}
        style={styles.input}
        secureTextEntry
      />
      {error ? <Text style={{ color: "red" }}>{error}</Text> : null}
      {loading ? (
        <ActivityIndicator size="large" color="#0066cc" />
      ) : (
        <Pressable style={styles.button} onPress={handleAuth}>
          <Text style={styles.buttonText}>
            {mode === "login" ? "Entrar" : "Registrarse"}
          </Text>
        </Pressable>
      )}
      <Pressable
        onPress={() => setMode(mode === "login" ? "register" : "login")}
      >
        <Text style={styles.toggle}>
          {mode === "login"
            ? "¿No tienes cuenta? Regístrate"
            : "¿Ya tienes cuenta? Inicia sesión"}
        </Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    justifyContent: "center",
    backgroundColor: "#f9fafb",
  },
  title: {
    fontSize: 24,
    fontWeight: "600",
    marginBottom: 24,
    textAlign: "center",
    color: "#111827",
  },
  input: {
    height: 48,
    borderColor: "#d1d5db",
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 12,
    marginBottom: 16,
    backgroundColor: "#fff",
  },
  button: {
    height: 48,
    backgroundColor: "#2563eb",
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 12,
  },
  buttonText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "600",
  },
  toggle: {
    color: "#2563eb",
    textAlign: "center",
    marginTop: 8,
  },
});
