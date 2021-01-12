//@main
//var gl = $('<canvas/>').appendTo($('#'+containerId)).attr('width', viewportWidth).attr('height', viewportHeight)[0].getContext('experimental-webgl', { depth : false });

//prog_copy = gl.createProgram();

//var posBuffer = gl.createBuffer();
//gl.bindBuffer(gl.ARRAY_BUFFER, posBuffer);

//var vertices = new Float32Array([ -1, -1, 0, 1, -1, 0, -1, 1, 0, 1, 1, 0 ]);

//gl.bufferData(gl.ARRAY_BUFFER, texCoordOffset + texCoords.byteLength, gl.STATIC_DRAW);

const width = 640;
const height = 480;

gl.viewport(0, 0, width, height);



advance();
composite();

function advance() {
  gl.viewport(0, 0, sizeX, sizeY);
  gl.useProgram(prog_advance);
  setUniforms(prog_advance);
  if (it > 0) {
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture_main_l); // interpolated input
    gl.activeTexture(gl.TEXTURE1);
    gl.bindTexture(gl.TEXTURE_2D, texture_main_n); // "nearest" input
    gl.bindFramebuffer(gl.FRAMEBUFFER, FBO_main2); // write to buffer
  } else {
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture_main2_l); // interpolated
    gl.activeTexture(gl.TEXTURE1);
    gl.bindTexture(gl.TEXTURE_2D, texture_main2_n); // "nearest"
    gl.bindFramebuffer(gl.FRAMEBUFFER, FBO_main); // write to buffer
  }
  gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
  gl.flush();

  calculateBlurTexture();
  it = -it;
}

function composite() {
  gl.viewport(0, 0, viewX, viewY);
  gl.useProgram(prog_composite);
  setUniforms(prog_composite);
  if (it < 0) {
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture_main_l);
    gl.activeTexture(gl.TEXTURE1);
    gl.bindTexture(gl.TEXTURE_2D, texture_main_n);
  } else {
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture_main2_l);
    gl.activeTexture(gl.TEXTURE1);
    gl.bindTexture(gl.TEXTURE_2D, texture_main2_n);
  }
  gl.bindFramebuffer(gl.FRAMEBUFFER, null);
  gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
  gl.flush();
}