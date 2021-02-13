const { ipcRenderer } = require('electron')

ipcRenderer.on('ld', (event, arg) => {
  filesChosen(["C:/Users/user/Documents/Folder1", "C:/Users/user/Documents/Folder2", "C:/Users/user/Documents/Folder3", "C:/Users/user/Documents/Folder4", "C:/Users/user/Documents/Folder5", "C:/Users/user/Documents/Folder6", "C:/Users/user/Documents/Folder7", "C:/Users/user/Documents/Folder8", "C:/Users/user/Documents/Folder9", "C:/Users/user/Documents/Folder10", "C:/Users/user/Documents/Folder11", "C:/Users/user/Documents/Folder12", "C:/Users/user/Documents/Folder13", "C:/Users/user/Documents/Folder14", "C:/Users/user/Documents/Folder15", "C:/Users/user/Documents/Folder16"])

  algosChosen(['Algo1', 'Algo2', 'Algo3'])
})

ipcRenderer.on('cycleInserted', (event, arg) => {
  tbody.querySelector(`tr[row_id="${arg.oldId}"] span[col=id]`).innerText = arg.newId
})

const fileChooser = () => {
  ipcRenderer.send('fileChooser', {})
}

ipcRenderer.on('filesChosen', (event, arg) => {
  filesChosen(arg.filePaths)
})

const filesChosen = (items) => {
  // console.log(items)
  const inputsTempl = document.querySelector('#inputsTemplate')
  const inputs = inputsTempl.content.cloneNode(true)
  for (el of items) {
    const inputTempl = document.querySelector('#inputItemTemplate')
    const input = inputTempl.content.cloneNode(true)
    input.querySelector('.item').innerText = el
    inputs.querySelector('.items').appendChild(input)
  }
  document.querySelector('#inputsCell > .items').replaceWith(inputs)
}

const algosChosen = (items) => {
  // console.log(items)
  const algosTempl = document.querySelector('#algosTemplate')
  const algos = algosTempl.content.cloneNode(true)
  for (el of items) {
    const algoTempl = document.querySelector('#algoItemTemplate')
    const algo = algoTempl.content.cloneNode(true)
    algo.querySelector('.item').innerText = el
    algos.querySelector('.items').appendChild(algo)
  }
  document.querySelector('#algosCell > .items').replaceWith(algos)
}