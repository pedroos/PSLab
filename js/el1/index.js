const { ipcMain, app, BrowserWindow, dialog, ipcRenderer } = require('electron')
const envPaths = require('env-paths')
const path = require('path')
const fs = require('fs')

let win
const paths = envPaths('El1')
const procDir = process.cwd()

const createWindow = () => {
  win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration: true
    }
  })

  win.webContents.openDevTools({ mode: 'detach' });

  win.loadFile('index.html')
}

const load = () => {  
  win.webContents.on('did-finish-load', () => {
    win.webContents.send('ld', {})
  })
}

app.whenReady()
  .then(createWindow)
  .then(load)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') 
    app.quit()
})

app.on('activate', () => {
  if (BrowserWindow.getAllWindows().length === 0) 
    createWindow()
})

ipcMain.on('fileChooser', (event, arg) => {
  console.log('fileChooser()')
  const open = dialog.showOpenDialog({properties: ['openDirectory']})
  open.then((r) => {
    if (r.canceled) return
    win.webContents.send('filesChosen', {filePaths: r.filePaths})
  })
})