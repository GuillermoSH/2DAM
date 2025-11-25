import { useRouter } from "expo-router";
import React, { useContext, useState } from "react";
import { Button, Text, TextInput, View, Alert } from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Login() {
  const { login, token } = useContext(AuthContext);
  const [expenses, setExpenses] = useState([
    {
      id: "1",
      desc: "Producto 1",
      amount: "2",
      paid_by: "e.paid_by",
    },
    {
      id: "2",
      desc: "Producto 2",
      amount: "1",
      paid_by: "e.paid_by",
    },
  ]);
  const router = useRouter();

  const handleDelete = (id: string) => {
    expenses.forEach((expense) => {
      if (expense.id == id) {
        expenses.splice(expenses.indexOf(expense), 1);
        setExpenses([...expenses]);
      }
    });
  };

  const handleEdit = (id: string) => {
    console.log("Editando id: " + id);
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      <Text>Mi grupo</Text>
      {expenses.map((e) => (
        <View key={e.id}>
          <Text
            onPress={() => router.replace("/groupdetail")}
          >{`Precio: ${e.amount} - ${e.desc}`}</Text>
          <Button title="Editar" onPress={() => handleEdit(e.id)} />
          <Button title="Borrar" onPress={() => handleDelete(e.id)} />
        </View>
      ))}

      <Button title="Volver a mis grupos" onPress={() => router.replace("/")} />
    </View>
  );
}
