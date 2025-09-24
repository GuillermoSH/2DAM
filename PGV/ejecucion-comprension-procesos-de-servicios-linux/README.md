# Code, Learn & Practice  
**Procesos y Servicios (modo alumno, sin root) — Trabajo en `$HOME/dam` con *user units* de systemd**

> **Importante:** Esta guía está adaptada para **usuarios sin privilegios de root**.  
> Todo se hace **en tu carpeta** `~/dam` usando **systemd --user** (un administrador por usuario), sin tocar `/etc` ni `/usr/local`.  
> Pega **salidas reales** y responde a las preguntas cortas.

---

## Preparación

Crea tu área de trabajo y variables útiles:

```bash
mkdir -p ~/dam/{bin,logs,units}
export DAM=~/dam
echo 'export DAM=~/dam' >> ~/.bashrc
```

Comprobar versión de systemd y que el *user manager* está activo:

```bash
systemctl --user --version | head -n1
systemctl --user status --no-pager | head -n5
```
**Pega salida aquí:**

```text
systemd 255 (255.4-1ubuntu8.6)

● a108pc09
    State: running
    Units: 293 loaded (incl. loaded aliases)
     Jobs: 0 queued
   Failed: 0 units
```

**Reflexiona la salida:**

```text
Nos esta dando la versión del usuario que está ejecutandose y con el segundo comando, el nombre del usuario, el estado en el que se encuentra, los procesos que tiene ejecutandose actualmente, los que estan en segundo plano (en cola) y que han fallado.
```

---

## Bloque 1 — Conceptos (breve + fuentes)

1) ¿Qué es **systemd** y en qué se diferencia de SysV init?  

**Respuesta:**  
systemd es un conjunto de bloques basicos para la construccion del sistema Linux. Entrega un sistema y un administrador de servicios que se ejecuta como PID 1 y arranca el resto del sistema.

_Fuentes:_ https://systemd.io/

2) **Servicio** vs **proceso** (ejemplos).  

**Respuesta:**  
Procesos se producen al interactuar directamente con una aplicacion o programa y generalmente corren en primer plano y los servicios son necesarios del sistema y corren en segundo plano.

_Fuentes:_ https://www.reviversoft.com/es/blog/2013/08/processes-and-services-in-windows/

3) ¿Qué son los **cgroups** y para qué sirven?  

**Respuesta:**  
Son una herramienta para crear agrupaciones jerarquicamente de procesos para que se puedan definir eficientemente la manera en la que se asignan recursos a cada uno de ellos.

_Fuentes:_ https://elpuig.xeill.net/Members/vcarceler/articulos/introduccion-a-los-grupos-de-control-cgroups-de-linux

4) ¿Qué es un **unit file** y tipos (`service`, `timer`, `socket`, `target`)?  

**Respuesta:**  
Son archivos de configuracion de asignacion de recursos de las units. Existen varios tipos de ellas:
- service: dicta como administrar un servicio o aplicacion en el servidor.
- socket: describe una red o socket IPC, o un buffer FIFO que systemd usa para la activacion basada en sockets.
- target: proporciona puntos de sincronizado entre otras units cuando arrancan o cambian de estado.
- timer: define un timer administrado por systemd, similar a un cron job para una activacion desfasada o programada

_Fuentes:_

5) ¿Qué hace `journalctl` y cómo ver logs **de usuario**?  

**Respuesta:**  
Muestra los registros del demonio journald, que gestiona todos los mensajes producidos por el kernel, servicios, etc. Para ver los logs del usuario habra que usar el comando `journalctl _UID=[id usuario (id -u nombreUsuario)]`.

_Fuentes:_ https://www.digitalocean.com/community/tutorials/how-to-use-journalctl-to-view-and-manipulate-systemd-logs-es

---

## Bloque 2 — Práctica guiada (todo en tu `$DAM`)

> Si un comando pide permisos que no tienes, usa la **versión `--user`** o consulta el **ayuda** con `--help` para alternativas.

### 2.1 — PIDs básicos

**11.** PID de tu shell y su PPID.

```bash
echo "PID=$$  PPID=$PPID"
```
**Salida:**

```text
PID=7045  PPID=6989
```

**Pregunta:** ¿Qué proceso es el padre (PPID) de tu shell ahora?  

**Respuesta:**
6989 es el PPID
---

**12.** PID del `systemd --user` (manager de usuario) y explicación.

```bash
pidof systemd --user || pgrep -u "$USER" -x systemd
```

**Salida:**

```text
3416
```
**Pregunta:** ¿Qué hace el *user manager* de systemd para tu sesión?  

**Respuesta:**

---

### 2.2 — Servicios **de usuario** con systemd

Vamos a crear un servicio sencillo y un timer **en tu carpeta** `~/.config/systemd/user` o en `$DAM/units` (usaremos la primera para que `systemctl --user` lo encuentre).

**13.** Prepara directorios y script de práctica.

```bash
mkdir -p ~/.config/systemd/user "$DAM"/{bin,logs,units}
cat << 'EOF' > "$DAM/bin/fecha_log.sh"
#!/usr/bin/env bash
mkdir -p "$HOME/dam/logs"
echo "$(date --iso-8601=seconds) :: hello from user timer" >> "$HOME/dam/logs/fecha.log"
EOF
chmod +x "$DAM/bin/fecha_log.sh"
```

**14.** Crea el servicio **de usuario** `fecha-log.service` (**Type=simple**, ejecuta tu script).

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.service
[Unit]
Description=Escribe fecha en $HOME/dam/logs/fecha.log

[Service]
Type=simple
ExecStart=%h/dam/bin/fecha_log.sh
EOF

systemctl --user daemon-reload
systemctl --user start fecha-log.service
systemctl --user status fecha-log.service --no-pager -l | sed -n '1,10p'
```
**Salida (pega un extracto):**

```text
○ fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log
     Loaded: loaded (/home/dam/.config/systemd/user/fecha-log.service; static)
     Active: inactive (dead)

sep 24 16:26:11 a108pc09 systemd[3358]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 24 16:27:09 a108pc09 systemd[3358]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.

```
**Pregunta:** ¿Se creó/actualizó `~/dam/logs/fecha.log`? Muestra las últimas líneas:

```bash
tail -n 5 "$DAM/logs/fecha.log"
```

**Salida:**

```text
2025-09-24T16:26:11+01:00 :: hello from user timer
2025-09-24T16:27:09+01:00 :: hello from user timer
```

**Reflexiona la salida:**

```text
Se ejecuto dos veces la escritura en el fichero log y se registro las horas a las que el timer fue ejecutado.
```

---

**15.** Diferencia **enable** vs **start** (modo usuario). Habilita el **timer**.

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.timer
[Unit]
Description=Timer (usuario): ejecuta fecha-log.service cada minuto

[Timer]
OnCalendar=*:0/1
Unit=fecha-log.service
Persistent=true

[Install]
WantedBy=timers.target
EOF

systemctl --user daemon-reload
systemctl --user enable --now fecha-log.timer
systemctl --user list-timers --all | grep fecha-log || true
```

**Salida (recorta):**

```text
Created symlink /home/dam/.config/systemd/user/timers.target.wants/fecha-log.timer → /home/dam/.config/systemd/user/fecha-log.timer.
Wed 2025-09-24 16:30:00 WEST  43s -                                       - fecha-log.timer                fecha-log.service
```
**Pregunta:** ¿Qué diferencia hay entre `enable` y `start` cuando usas `systemctl --user`?  

**Respuesta:**
Enable es cuando quieres que se ejecute en el arranque del sistema y start solo por esta sesión.

---

**16.** Logs recientes **del servicio de usuario** con `journalctl --user`.

```bash
journalctl --user -u fecha-log.service -n 10 --no-pager
```

**Salida:**

```text
sep 24 16:26:11 a108pc09 systemd[3358]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 24 16:27:09 a108pc09 systemd[3358]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 24 16:30:08 a108pc09 systemd[3358]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.

```
**Pregunta:** ¿Ves ejecuciones activadas por el timer? ¿Cuándo fue la última?  

**Respuesta:**
Si, 3 en total y la última fue el 24 de septiembre a las 16:30:08

---

### 2.3 — Observación de procesos sin root

**17.** Puertos en escucha (lo que puedas ver como usuario).

```bash
lsof -i -P -n | grep LISTEN || ss -lntp
```
**Salida:**

```text
java      11961  dam  109u  IPv6  62605      0t0  TCP 127.0.0.1:33481 (LISTEN)
java      12448  dam  307u  IPv6  59568      0t0  TCP 127.0.0.1:40783 (LISTEN)
java      12448  dam  391u  IPv6  62707      0t0  TCP 127.0.0.1:43207 (LISTEN)
java      12801  dam   17u  IPv6  53807      0t0  TCP 127.0.0.1:17907 (LISTEN)
```
**Pregunta:** ¿Qué procesos *tuyos* están escuchando? (si no hay, explica por qué)  

**Respuesta:**
Los 4 que se han dado en la salida del comando ejecutado

---

**18.** Ejecuta un proceso bajo **cgroup de usuario** con límite de memoria.

```bash
systemd-run --user --scope -p MemoryMax=50M sleep 200
ps -eo pid,ppid,cmd,stat | grep "[s]leep 200"
```

**Salida:**

```text
Running as unit: run-r1ed2e8bb271043029942f6bbb806545a.scope; invocation ID: dd7d33f6d45d468d9cd98920e4125728
```
**Pregunta:** ¿Qué ventaja tiene lanzar con `systemd-run --user` respecto a ejecutarlo “a pelo”?  

**Respuesta:**


---

**19.** Observa CPU en tiempo real con `top` (si tienes `htop`, también vale).

```bash
top -b -n 1 | head -n 15
```
**Salida (resumen):**

```text
top - 16:32:59 up  1:57,  1 user,  load average: 0,58, 0,51, 0,71
Tareas: 407 total,   1 ejecutar,  405 hibernar,    0 detener,    1 zombie
%Cpu(s):  0,6 us,  0,0 sy,  0,0 ni, 98,1 id,  1,3 wa,  0,0 hi,  0,0 si,  0,0 st 
MiB Mem :  31453,3 total,  23104,8 libre,   4228,4 usado,   4629,8 búf/caché    
MiB Intercambio:   2048,0 total,   2048,0 libre,      0,0 usado.  27224,8 dispon

    PID USUARIO   PR  NI    VIRT    RES    SHR S  %CPU  %MEM     HORA+ ORDEN
      1 root      20   0   23240  13684   9204 S   0,0   0,0   0:01.59 systemd
      2 root      20   0       0      0      0 S   0,0   0,0   0:00.01 kthreadd
      3 root      20   0       0      0      0 S   0,0   0,0   0:00.00 pool_wo+

```
**Pregunta:** ¿Cuál es tu proceso con mayor `%CPU` en ese momento?  

**Respuesta:**
Procesos de firefox que no salen en la salida por estar ordenados por PID con un 5.6% de la CPU

---

**20.** Traza syscalls de **tu propio proceso** (p. ej., el `sleep` anterior).
> Nota: Sin root, no podrás adjuntarte a procesos de otros usuarios ni a algunos del sistema.

```bash
pid=$(pgrep -u "$USER" -x sleep | head -n1)
strace -p "$pid" -e trace=nanosleep -tt -c -f 2>&1 | sed -n '1,10p'
```

**Salida (fragmento):**

```text

```
**Pregunta:** Explica brevemente la syscall que observaste.  

**Respuesta:**

---

### 2.4 — Estados y jerarquía (sin root)

**21.** Árbol de procesos con PIDs.

```bash
pstree -p | head -n 50
```

**Salida (recorta):**

```text
systemd(1)-+-ModemManager(1860)-+-{ModemManager}(1870)
           |                    |-{ModemManager}(1872)
           |                    `-{ModemManager}(1874)
           |-NetworkManager(1828)-+-{NetworkManager}(1865)
           |                      |-{NetworkManager}(1866)
           |                      `-{NetworkManager}(1867)
           |-accounts-daemon(1159)-+-{accounts-daemon}(1198)
           |                       |-{accounts-daemon}(1199)
           |                       `-{accounts-daemon}(1830)
           |-agetty(2261)
           |-apache2(2323)-+-apache2(2337)
           |               |-apache2(2339)
           |               |-apache2(2340)
           |               |-apache2(2341)
           |               `-apache2(2343)
           |-at-spi2-registr(3712)-+-{at-spi2-registr}(3718)
           |                       |-{at-spi2-registr}(3719)
           |                       `-{at-spi2-registr}(3720)
           |-avahi-daemon(1165)---avahi-daemon(1259)
           |-blkmapd(1098)
           |-chrome_crashpad(4201)-+-{chrome_crashpad}(4202)
           |                       `-{chrome_crashpad}(4203)
```
**Pregunta:** ¿Bajo qué proceso aparece tu `systemd --user`?  

**Respuesta:**
De systemd PID 1

---

**22.** Estados y relación PID/PPID.

```bash
ps -eo pid,ppid,stat,cmd | head -n 20
```
**Salida:**

```text
    PID    PPID STAT CMD
      1       0 Ss   /sbin/init splash
      2       0 S    [kthreadd]
      3       2 S    [pool_workqueue_release]
      4       2 I<   [kworker/R-rcu_g]
      5       2 I<   [kworker/R-rcu_p]
      6       2 I<   [kworker/R-slub_]
      7       2 I<   [kworker/R-netns]
     10       2 I<   [kworker/0:0H-events_highpri]
     12       2 I<   [kworker/R-mm_pe]
     13       2 I    [rcu_tasks_kthread]
     14       2 I    [rcu_tasks_rude_kthread]
     15       2 I    [rcu_tasks_trace_kthread]
     16       2 S    [ksoftirqd/0]
     17       2 I    [rcu_preempt]
     18       2 S    [migration/0]
     19       2 S    [idle_inject/0]
     20       2 S    [cpuhp/0]
     21       2 S    [cpuhp/1]
     22       2 S    [idle_inject/1]

```
**Pregunta:** Explica 3 flags de `STAT` que veas (ej.: `R`, `S`, `T`, `Z`, `+`).  

**Respuesta:**
- Ss: sleep ininterrumpible que es lider de sesion
- I<: hilo del kernel suspendido con prioridad alta
- S: sleep ininterrumpible
- I: hilo del kernel
- R: ejecutable o en ejecucion

---

**23.** Suspende y reanuda **uno de tus procesos** (no crítico).

```bash
# Crea un proceso propio en segundo plano
sleep 120 &
pid=$!
# Suspende
kill -STOP "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
# Reanuda
kill -CONT "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
```
**Pega los dos estados (antes/después):**

```text
[1] 68246

[1]+  Detenido                sleep 120
    PID STAT CMD
  68246 T    bash
    PID STAT CMD
  68246 S    sleep 120

```
**Pregunta:** ¿Qué flag indicó la suspensión?  

**Respuesta:**
La S

---

**24. (Opcional)** Genera un **zombie** controlado (no requiere root).

```bash
cat << 'EOF' > "$DAM/bin/zombie.c"
#include <stdlib.h>
#include <unistd.h>
int main() {
  if (fork() == 0) { exit(0); } // hijo termina
  sleep(60); // padre no hace wait(), hijo queda zombie hasta que padre termine
  return 0;
}
EOF
gcc "$DAM/bin/zombie.c" -o "$DAM/bin/zombie" && "$DAM/bin/zombie" &
ps -el | grep ' Z '
```
**Salida (recorta):**

```text
[2] 68597
0 Z  1001   41495   41483  0  80   0 -     0 -      ?        00:00:00 sd_espeak-ng-mb
```
**Pregunta:** ¿Por qué el estado `Z` y qué lo limpia finalmente?  

**Respuesta:**
Z para crear un proceso zombie (esperando a que el padre reciba su estado de finalizacion)

---

### 2.5 — Limpieza (solo tu usuario)

Detén y deshabilita tu **timer/servicio de usuario** y borra artefactos si quieres.

```bash
systemctl --user disable --now fecha-log.timer
systemctl --user stop fecha-log.service
rm -f ~/.config/systemd/user/fecha-log.{service,timer}
systemctl --user daemon-reload
rm -rf "$DAM/bin" "$DAM/logs" "$DAM/units"
```

---

## ¿Qué estás prácticando?
- [ ] Pegaste **salidas reales**.  
- [ ] Explicaste **qué significan**.  
- [ ] Usaste **systemd --user** y **journalctl --user**.  
- [ ] Probaste `systemd-run --user` con límites de memoria.  
- [ ] Practicaste señales (`STOP`/`CONT`), `pstree`, `ps` y `strace` **sobre tus procesos**.

---

## Licencia 📄
Licencia **Apache 2.0** — ver [LICENSE.md](https://github.com/jpexposito/code-learn-practice/blob/main/LICENSE).