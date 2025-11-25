import { useRouter } from "expo-router";
import React, { useContext, useState } from "react";
import { Button, Text, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Register() {
  const { register } = useContext(AuthContext);
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");
  const [error, setError] = useState("");

  const handleRegister = async () => {
    if (password === confirm) {
      var error = "La contraseña debe:\n";
      if (password.length < 8) error += "- Tener al menos 8 caracteres\n";
      if (!password.match(/[A-Z]/)) error += "- Incluir al menos una mayúscula\n";
      if (!password.match(/[a-z]/)) error += "- Incluir al menos una minúscula\n";
      if (!password.match(/\d/)) error += "- Incluir al menos un número\n";
      if (!password.match(/[!#$%&?\.,+_-]/)) error += "- Incluir al menos un símbolo\n";

      if (error === "") {
        const res = await register(username, password);
        if (res.ok) {
          router.replace("/login");
        } else {
          setError(res.msg);
        }
      } else {
        setError(error);
      }
    } else {
      setError("Las contraseñas deben coincidir");
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      <TextInput
        placeholder="Usuario"
        value={username}
        onChangeText={setUsername}
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      <TextInput
        placeholder="Repetir contraseña"
        value={confirm}
        onChangeText={setConfirm}
        secureTextEntry
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      {error ? <Text style={{ color: "red" }}>{error}</Text> : null}
      <Button title="Registrar" onPress={handleRegister} />
      <Button title="Volver al login" onPress={() => router.push("/login")} />
    </View>
  );
}
