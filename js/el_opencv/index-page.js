const { ipcRenderer } = require('electron')

ipcRenderer.on('ld', (event, arg) => {
  filesChosen(["C:/Users/pedro/Documents/Ample Sound", "C:/Users/pedro/Documents/Audacity", "C:/Users/pedro/Documents/Custom Office Templates", "C:/Users/pedro/Documents/IISExpress", "C:/Users/pedro/Documents/Line 6", "C:/Users/pedro/Documents/MATLAB", "C:/Users/pedro/Documents/My Kindle Content", "C:/Users/pedro/Documents/My Web Sites", "C:/Users/pedro/Documents/MyOpenCv", "C:/Users/pedro/Documents/Native Instruments", "C:/Users/pedro/Documents/Processing", "C:/Users/pedro/Documents/Python Scripts", "C:/Users/pedro/Documents/Renoise", "C:/Users/pedro/Documents/SQL Server Management Studio", "C:/Users/pedro/Documents/Visual Studio 2012", "C:/Users/pedro/Documents/Visual Studio 2017"])

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